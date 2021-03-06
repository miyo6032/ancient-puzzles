package com.barribob.ancient_puzzles.puzzle_manager.reward_event

import com.barribob.ancient_puzzles.Mod
import com.barribob.ancient_puzzles.blocks.AncientChestBlockEntity
import com.barribob.ancient_puzzles.randomPitch
import com.barribob.ancient_puzzles.readBlockPos
import com.barribob.ancient_puzzles.toNbt
import net.minecraft.block.Blocks
import net.minecraft.block.ChestBlock
import net.minecraft.block.entity.ChestBlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.sound.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

private const val positionsKey = "chest_positions"

class AncientChestRewardEvent() : RewardEvent {
    private var chestPositions: MutableList<BlockPos> = mutableListOf()

    constructor(nbtCompound: NbtCompound) : this() {
        chestPositions = nbtCompound.getList(positionsKey, NbtCompound().type.toInt()).filterIsInstance<NbtCompound>().map(::readBlockPos).toMutableList()
    }

    override fun doEvent(world: World) {
        chestPositions.forEach { tryChangeToChest(world, it) }
    }

    private fun tryChangeToChest(world: World, blockPos: BlockPos) {
        val blockEntity = world.getBlockEntity(blockPos)
        if (blockEntity is AncientChestBlockEntity) {
            val oldBlockState = world.getBlockState(blockPos)
            world.breakBlock(blockPos, false)
            world.setBlockState(blockPos, Blocks.CHEST.defaultState.with(ChestBlock.FACING, oldBlockState.get(ChestBlock.FACING)).with(ChestBlock.WATERLOGGED, oldBlockState.get(ChestBlock.WATERLOGGED)))
            world.playSound(null, blockPos, Mod.sounds.chestAppear, SoundCategory.BLOCKS, 1.0f, world.random.randomPitch())
            val newBlockEntity = world.getBlockEntity(blockPos)
            if(newBlockEntity is ChestBlockEntity) {
                blockEntity.copyToEntity(newBlockEntity)
            }
        }
    }

    fun addChestPosition(pos: BlockPos) {
        chestPositions.add(pos)
    }

    override fun toNbt(): NbtCompound {
        val compound = NbtCompound()
        val positions = NbtList()
        for (pos in chestPositions) {
            positions.add(pos.toNbt())
        }
        compound.put(positionsKey, positions)
        return compound
    }
}