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

package io.github.moulberry.notenoughupdates.mixins;

import io.github.moulberry.notenoughupdates.cosmetics.CapeManager;
import io.github.moulberry.notenoughupdates.events.RenderParticleEvent;
import io.github.moulberry.notenoughupdates.miscfeatures.CustomItemEffects;
import io.github.moulberry.notenoughupdates.util.Utils;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumWorldBlockLayer;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderGlobal.class)
public class MixinRenderGlobal {
	@ModifyVariable(method = "setupTerrain", at = @At(value = "STORE"), ordinal = 4)
	public double setupTerrain_d0(double d3) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.x;
		}
		return d3;
	}

	@ModifyVariable(method = "setupTerrain", at = @At(value = "STORE"), ordinal = 5)
	public double setupTerrain_d1(double d4) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.y;
		}
		return d4;
	}

	@ModifyVariable(method = "setupTerrain", at = @At(value = "STORE"), ordinal = 6)
	public double setupTerrain_d2(double d5) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.z;
		}
		return d5;
	}

	//renderEntities
	@ModifyVariable(method = "renderEntities", at = @At(value = "STORE"), ordinal = 3)
	public double renderEntities_d0(double d3) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.x;
		}
		return d3;
	}

	@ModifyVariable(method = "renderEntities", at = @At(value = "STORE"), ordinal = 4)
	public double renderEntities_d1(double d4) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.y;
		}
		return d4;
	}

	@ModifyVariable(method = "renderEntities", at = @At(value = "STORE"), ordinal = 5)
	public double renderEntities_d2(double d5) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.z;
		}
		return d5;
	}

	@Inject(method = "renderBlockLayer", at = @At("RETURN"))
	public void renderBlockLayer(
		EnumWorldBlockLayer blockLayerIn, double partialTicks, int pass,
		Entity entityIn, CallbackInfoReturnable<Integer> cir
	) {
		if (blockLayerIn == EnumWorldBlockLayer.CUTOUT) {
			CapeManager.getInstance().postRenderBlocks();
		}
	}

	//drawBlockDamageTexture
	@ModifyVariable(method = "drawBlockDamageTexture", at = @At(value = "STORE"), ordinal = 0)
	public double drawBlockDamageTexture_d0(double d0) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.x;
		}
		return d0;
	}

	@ModifyVariable(method = "drawBlockDamageTexture", at = @At(value = "STORE"), ordinal = 1)
	public double drawBlockDamageTexture_d1(double d1) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.y;
		}
		return d1;
	}

	@ModifyVariable(method = "drawBlockDamageTexture", at = @At(value = "STORE"), ordinal = 2)
	public double drawBlockDamageTexture_d2(double d2) {
		Vector3f currentPosition = CustomItemEffects.INSTANCE.getCurrentPosition();
		if (currentPosition != null) {
			return currentPosition.z;
		}
		return d2;
	}

	@Inject(method = "spawnParticle(IZDDDDDD[I)V", at = @At("HEAD"), cancellable = true)
	private void spawnParticle(int particleId, boolean ignoreRange, double x, double y, double z, double xOffset, double yOffset, double zOffset, int[] p_180442_15_, CallbackInfo ci) {
		String callerClass = Utils.getCallerClass(
			"io.github.moulberry.notenoughupdates.mixins.MixinRenderGlobal",
			"net.minecraft.client.renderer.RenderGlobal",
			"net.minecraft.world.World",
			"net.minecraft.client.network.NetHandlerPlayClient",
			"net.minecraft.network.play.server.S2APacketParticles"
		);
		if (callerClass == null) {
			callerClass = "null";
		}
		System.out.println("callerClass: " + callerClass);
		if (new RenderParticleEvent(particleId, callerClass, x, y, z).post()) {
			ci.cancel();
		}

	}
}
