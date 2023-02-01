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

package io.github.moulberry.notenoughupdates.commands;

import io.github.moulberry.notenoughupdates.NotEnoughUpdates;
import io.github.moulberry.notenoughupdates.commands.dev.DiagCommand;
import io.github.moulberry.notenoughupdates.commands.dev.PackDevCommand;
import io.github.moulberry.notenoughupdates.commands.dungeon.DhCommand;
import io.github.moulberry.notenoughupdates.commands.dungeon.DnCommand;
import io.github.moulberry.notenoughupdates.commands.dungeon.JoinDungeonCommand;
import io.github.moulberry.notenoughupdates.commands.dungeon.MapCommand;
import io.github.moulberry.notenoughupdates.commands.misc.AhCommand;
import io.github.moulberry.notenoughupdates.commands.misc.CalculatorCommand;
import io.github.moulberry.notenoughupdates.commands.misc.CalendarCommand;
import io.github.moulberry.notenoughupdates.commands.misc.CosmeticsCommand;
import io.github.moulberry.notenoughupdates.commands.misc.CustomizeCommand;
import io.github.moulberry.notenoughupdates.commands.misc.PronounsCommand;
import io.github.moulberry.notenoughupdates.commands.misc.UpdateCommand;
import io.github.moulberry.notenoughupdates.commands.profile.CataCommand;
import io.github.moulberry.notenoughupdates.commands.profile.PeekCommand;
import io.github.moulberry.notenoughupdates.commands.profile.PvCommand;
import io.github.moulberry.notenoughupdates.commands.profile.ViewProfileCommand;
import io.github.moulberry.notenoughupdates.miscfeatures.FairySouls;
import io.github.moulberry.notenoughupdates.miscgui.GuiEnchantColour;
import io.github.moulberry.notenoughupdates.miscgui.GuiInvButtonEditor;
import io.github.moulberry.notenoughupdates.miscgui.NEUOverlayPlacements;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Loader;

public class Commands {
	public Commands() {

		// Help Commands

		// Dev Commands
		ClientCommandHandler.instance.registerCommand(new PackDevCommand());
		ClientCommandHandler.instance.registerCommand(new DiagCommand());

		// Profile Commands
		ClientCommandHandler.instance.registerCommand(new PeekCommand());
		ClientCommandHandler.instance.registerCommand(new ViewProfileCommand());
		ClientCommandHandler.instance.registerCommand(new PvCommand());
		if (!Loader.isModLoaded("skyblockextras")) ClientCommandHandler.instance.registerCommand(new CataCommand());

		// Dungeon Commands
		ClientCommandHandler.instance.registerCommand(new MapCommand());
		ClientCommandHandler.instance.registerCommand(new JoinDungeonCommand());
		ClientCommandHandler.instance.registerCommand(new DnCommand());
		ClientCommandHandler.instance.registerCommand(new DhCommand());

		// Misc Commands
		ClientCommandHandler.instance.registerCommand(new CosmeticsCommand());
		ClientCommandHandler.instance.registerCommand(new CustomizeCommand());
		ClientCommandHandler.instance.registerCommand(new ScreenCommand("neubuttons", GuiInvButtonEditor::new));
		ClientCommandHandler.instance.registerCommand(new ScreenCommand("neuec", GuiEnchantColour::new));
		ClientCommandHandler.instance.registerCommand(new ScreenCommand("neuoverlay", NEUOverlayPlacements::new));
		ClientCommandHandler.instance.registerCommand(new AhCommand());
		ClientCommandHandler.instance.registerCommand(new CalculatorCommand());
		ClientCommandHandler.instance.registerCommand(new CalendarCommand());
		ClientCommandHandler.instance.registerCommand(new UpdateCommand(NotEnoughUpdates.INSTANCE));
		ClientCommandHandler.instance.registerCommand(new PronounsCommand());

		// Fairy Soul Commands
		ClientCommandHandler.instance.registerCommand(new FairySouls.FairySoulsCommand());
	}
}
