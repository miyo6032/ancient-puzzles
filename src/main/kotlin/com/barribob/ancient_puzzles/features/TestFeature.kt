package com.barribob.ancient_puzzles.features

import com.barribob.ancient_puzzles.Mod
import com.barribob.ancient_puzzles.addPuzzle
import com.barribob.ancient_puzzles.puzzle_manager.PressAllBlocksPuzzleManager
import com.mojang.serialization.Codec
import net.minecraft.block.Block
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class TestFeature(configCodec: Codec<DefaultFeatureConfig>?) : Feature<DefaultFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<DefaultFeatureConfig>): Boolean {
        val chunk = context.world.getChunk(context.origin)

        context.world.setBlockState(context.origin, Mod.blocks.inputBlock.defaultState, Block.NOTIFY_LISTENERS)

        chunk.addPuzzle(PressAllBlocksPuzzleManager(listOf(context.origin)))
        return true
    }
}