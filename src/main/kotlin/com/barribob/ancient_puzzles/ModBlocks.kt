package com.barribob.ancient_puzzles

import com.barribob.ancient_puzzles.blocks.InputBlock
import com.barribob.ancient_puzzles.blocks.TimedPressurePlate
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.block.Material
import net.minecraft.block.PressurePlateBlock
import net.minecraft.item.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ModBlocks {
    val inputBlock = InputBlock(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(-1.0f, 3600000f).dropsNothing())
    val ancientPressurePlate = TimedPressurePlate(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.of(Material.STONE).requiresTool().noCollision().strength(3.0f))

    fun init() {
        registerBlockAndItem(Mod.identifier("input_block"), inputBlock, FabricItemSettings().group(Mod.itemGroup))
        registerBlockAndItem(Mod.identifier("ancient_pressure_plate"), ancientPressurePlate, FabricItemSettings().group(Mod.itemGroup))
    }

    private fun registerBlockAndItem(identifier: Identifier, block: Block, fabricItemSettings: FabricItemSettings = FabricItemSettings()) {
        Registry.register(Registry.BLOCK, identifier, block)
        Registry.register(Registry.ITEM, identifier, BlockItem(block, fabricItemSettings))
    }
}