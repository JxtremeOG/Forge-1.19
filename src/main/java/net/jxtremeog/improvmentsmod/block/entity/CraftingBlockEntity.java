package net.jxtremeog.improvmentsmod.block.entity;

import net.jxtremeog.improvmentsmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CraftingBlockEntity extends BlockEntity{
    public static final int numberOfSlots = 10;
    public static final int outputSlotId = 9;
    private final ItemStackHandler itemHandler = new ItemStackHandler(numberOfSlots){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1/*72*/;

    public CraftingBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRAFTING_BLOCK.get(), pPos, pBlockState);
        //MIGHT NOT NEED
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CraftingBlockEntity.this.progress;
                    case 1 -> CraftingBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CraftingBlockEntity.this.progress = value;
                    case 1 -> CraftingBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    //THIS IS WHAT MIGHT BREAK

//    @Override
//    public Component getDisplayName() {
//        return Component.literal("Tier One Workbench");
//    }
//
//    @Nullable
//    @Override
//    public AbstractContainerMenu createMenu(int id, Inventory pPlayerInventory, Player pPlayer) {
//        return new CraftingMenu2(id, pPlayerInventory, this, this.data);
//    }




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

    //THIS SHOULD BE OK
    public static void tick(Level level, BlockPos blockPos, BlockState blockState, CraftingBlockEntity pEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level, blockPos, blockState);

            if(pEntity.progress >= pEntity.maxProgress){
                craftItem(pEntity);
            }
        }else{
            pEntity.resetProgress();
            setChanged(level, blockPos, blockState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CraftingBlockEntity pEntity) {

        if(hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(4, 1, false);
            pEntity.itemHandler.setStackInSlot(outputSlotId, new ItemStack(ModItems.ZIRCON.get(),
                    pEntity.itemHandler.getStackInSlot(outputSlotId).getCount() + 1));

            pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(CraftingBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasRawGemInFirstSlot = entity.itemHandler.getStackInSlot(4).getItem() == ModItems.RAW_ZIRCON.get();

        return hasRawGemInFirstSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(ModItems.ZIRCON.get(), 1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(outputSlotId).getItem() == stack.getItem() || inventory.getItem(outputSlotId).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return 1 > inventory.getItem(outputSlotId).getCount();

    }
}
