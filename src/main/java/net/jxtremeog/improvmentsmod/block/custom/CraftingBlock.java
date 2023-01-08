package net.jxtremeog.improvmentsmod.block.custom;

import net.jxtremeog.improvmentsmod.block.entity.CraftingBlockEntity;
import net.jxtremeog.improvmentsmod.block.entity.ModBlockEntities;
import net.jxtremeog.improvmentsmod.block.entity.TierTwoBlockEntity;
import net.jxtremeog.improvmentsmod.screen.CraftingScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class CraftingBlock extends BaseEntityBlock {
    //Block Entity
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }


    //CraftingTableBlock
//    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

//    public CraftingTableBlock(BlockBehaviour.Properties pProperties) {
//        super(pProperties);
//    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

//    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
//        return new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) -> {
//            return new CraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos));
//        }, CONTAINER_TITLE);
//    }

    //CraftingBlock
    private static final Component GUI_TITLE = Component.translatable("container.crafting");

    public CraftingBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

//    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
//        return new SimpleMenuProvider((id, inventory, entity) -> new CraftingScreen(id, inventory, ContainerLevelAccess.create((Level)worldIn, (BlockPos)pos), (Block)this), GUI_TITLE);
//    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CraftingBlockEntity(pPos, pState);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
                                                                  BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.CRAFTING_BLOCK.get(),
                CraftingBlockEntity::tick);
    }
}
