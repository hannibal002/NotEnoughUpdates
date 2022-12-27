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

package io.github.moulberry.notenoughupdates.skyhanni.events

import io.github.moulberry.notenoughupdates.events.NEUEvent
import net.minecraft.network.Packet
import net.minecraftforge.fml.common.eventhandler.Cancelable

@Cancelable
open class PacketEvent(val packet: Packet<*>) : NEUEvent() {
    var direction: Direction? = null

    class ReceiveEvent(packet: Packet<*>) : PacketEvent(packet) {
        init {
            direction = Direction.INBOUND
        }
    }

    class SendEvent(packet: Packet<*>) : PacketEvent(packet) {
        init {
            direction = Direction.OUTBOUND
        }
    }

    enum class Direction {
        INBOUND, OUTBOUND
    }
}
