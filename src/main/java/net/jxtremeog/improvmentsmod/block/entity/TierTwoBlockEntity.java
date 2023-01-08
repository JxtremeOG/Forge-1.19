package net.jxtremeog.improvmentsmod.block.entity;

import net.jxtremeog.improvmentsmod.item.ModItems;
import net.jxtremeog.improvmentsmod.recipe.TierOneRecipe;
import net.jxtremeog.improvmentsmod.screen.TierTwoMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TierTwoBlockEntity extends BlockEntity implements MenuProvider {
    public static final int numberOfSlots = 10;
    public static final int outputSlotId = 9;
    private final ItemStackHandler itemHandler = new ItemStackHandler(numberOfSlots){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    //Passes crafting tick information
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1/*72*/;

    public TierTwoBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TIERTWO.get(), pPos, pBlockState);
        //MIGHT NOT NEED
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TierTwoBlockEntity.this.progress;
                    case 1 -> TierTwoBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TierTwoBlockEntity.this.progress = value;
                    case 1 -> TierTwoBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tier Two Workbench");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory pPlayerInventory, Player pPlayer) {
        return new TierTwoMenu(id, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

//    public static void tick(Level level, BlockPos blockPos, BlockState blockState, TierTwoBlockEntity pEntity) {
//        if(level.isClientSide()) {
//            return;
//        }
//
//        if(hasRecipe(pEntity)) {
//            pEntity.progress++;
//            setChanged(level, blockPos, blockState);
//
//            if(pEntity.progress >= pEntity.maxProgress){
//                craftItem(pEntity);
//            }
//        }else{
//            pEntity.resetProgress();
//            setChanged(level, blockPos, blockState);
//        }
//    }
//    private void resetProgress() {
//        this.progress = 0;
//    }
//
//    private static void craftItem(TierTwoBlockEntity pEntity) {
//        Level level = pEntity.level;
//        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
//        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
//            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
//        }
//
//        Optional<TierOneRecipe> recipe = level.getRecipeManager()
//                .getRecipeFor(TierOneRecipe.Type.WORKBENCH_ONE, inventory, level);
//
//        if(hasRecipe(pEntity)) {
//            pEntity.itemHandler.extractItem(4, 1, false);
//            pEntity.itemHandler.setStackInSlot(outputSlotId, new ItemStack(recipe.get().getResultItem().getItem(),
//                    pEntity.itemHandler.getStackInSlot(outputSlotId).getCount() + 1));
//
//            pEntity.resetProgress();
//        }
//    }
//
//    private static boolean hasRecipe(TierTwoBlockEntity entity) {
//        Level level = entity.level;
//        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
//        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
//            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
//        }
//
//        Optional<TierOneRecipe> recipe = level.getRecipeManager()
//                .getRecipeFor(TierOneRecipe.Type.WORKBENCH_ONE, inventory, level);
//
//        return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
//                canInsertItemIntoOutputSlot(inventory, recipe.get().getResultItem());
//    }
//
//    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
//        return inventory.getItem(outputSlotId).getItem() == stack.getItem() || inventory.getItem(outputSlotId).isEmpty();
//    }
//
//    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
//        return 1 > inventory.getItem(outputSlotId).getCount();
//
//    }
}
