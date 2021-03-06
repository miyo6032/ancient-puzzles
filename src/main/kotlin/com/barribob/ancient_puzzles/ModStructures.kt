package com.barribob.ancient_puzzles

import com.barribob.ancient_puzzles.mixins.StructureFeatureRegisterInvoker
import com.barribob.ancient_puzzles.structure.SurfaceStructureFeature
import com.barribob.ancient_puzzles.structure.TerminatorSurfaceStructureFeature
import com.barribob.ancient_puzzles.structure.processors.AncientChestProcessor
import com.barribob.ancient_puzzles.structure.processors.DirectionalLightPuzzleProcessor
import com.barribob.ancient_puzzles.structure.processors.PuzzleSetupStructureProcessor
import com.barribob.ancient_puzzles.structure.processors.PressAllBlocksPuzzleProcessor
import net.minecraft.structure.processor.StructureProcessorType
import net.minecraft.world.gen.GenerationStep

class ModStructures {
    val directionalLightPuzzleProcessor: StructureProcessorType<DirectionalLightPuzzleProcessor> = StructureProcessorType.register(Mod.identifier("directional_light_puzzle_processor").toString(),DirectionalLightPuzzleProcessor.CODEC)
    val pressAllBlocksPuzzleProcessor: StructureProcessorType<PressAllBlocksPuzzleProcessor> = StructureProcessorType.register(Mod.identifier("press_all_blocks_puzzle_processor").toString(), PressAllBlocksPuzzleProcessor.CODEC)
    val puzzleSetupStructureProcessor: StructureProcessorType<PuzzleSetupStructureProcessor> = StructureProcessorType.register(Mod.identifier("puzzle_setup_processor").toString(), PuzzleSetupStructureProcessor.CODEC)
    val ancientChestProcessor: StructureProcessorType<AncientChestProcessor> = StructureProcessorType.register(Mod.identifier("ancient_chest_processor").toString(), AncientChestProcessor.CODEC)

    fun init() {
        val identifier = Mod.identifier("press_all_blocks")
        val structureFeature = SurfaceStructureFeature()
        StructureFeatureRegisterInvoker.invokeRegister(identifier.toString(), structureFeature, GenerationStep.Feature.SURFACE_STRUCTURES)
        StructureFeatureRegisterInvoker.invokeRegister(Mod.identifier("pillar_combination").toString(), SurfaceStructureFeature(), GenerationStep.Feature.SURFACE_STRUCTURES)
        StructureFeatureRegisterInvoker.invokeRegister(Mod.identifier("directional_light").toString(), TerminatorSurfaceStructureFeature(), GenerationStep.Feature.SURFACE_STRUCTURES)
    }
}