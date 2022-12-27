/*
 * Copyright (C) 2022 NotEnoughUpdates contributors
 *
 * This file is part of NotEnoughUpdates.
 *
 * NotEnoughUpdates is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * NotEnoughUpdates is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotEnoughUpdates. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.moulberry.notenoughupdates.skyhanni

import io.github.moulberry.notenoughupdates.skyhanni.events.DrawScreenAfterEvent
import io.github.moulberry.notenoughupdates.skyhanni.events.GuiContainerEvent
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.Slot
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

class LorenzGuiContainerHook(guiAny: Any) {

    val gui: GuiContainer

    init {
        gui = guiAny as GuiContainer
    }

    fun closeWindowPressed(ci: CallbackInfo) {
        if (GuiContainerEvent.CloseWindowEvent(gui, gui.inventorySlots).post()) ci.cancel()
    }

    fun backgroundDrawn(mouseX: Int, mouseY: Int, partialTicks: Float, ci: CallbackInfo) {
        GuiContainerEvent.BackgroundDrawnEvent(gui, gui.inventorySlots, mouseX, mouseY, partialTicks).post()
    }

    fun onDrawSlot(slot: Slot, ci: CallbackInfo) {
        if (GuiContainerEvent.DrawSlotEvent.Pre(gui, gui.inventorySlots, slot).post()) ci.cancel()
    }

    fun onDrawSlotPost(slot: Slot, ci: CallbackInfo) {
        GuiContainerEvent.DrawSlotEvent.Post(gui, gui.inventorySlots, slot).post()
    }

    fun onMouseClick(slot: Slot?, slotId: Int, clickedButton: Int, clickType: Int, ci: CallbackInfo) {
        if (GuiContainerEvent.SlotClickEvent(gui, gui.inventorySlots, slot, slotId, clickedButton, clickType).post()) ci.cancel()
    }

    fun onDrawScreenAfter(
        mouseX: Int,
        mouseY: Int,
        ci: CallbackInfo,
    ) {
        if (DrawScreenAfterEvent(mouseX, mouseY, ci).post()) ci.cancel()
    }
}
