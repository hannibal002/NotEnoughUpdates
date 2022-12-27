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

import io.github.moulberry.notenoughupdates.NotEnoughUpdates;
import io.github.moulberry.notenoughupdates.events.RenderMobColoredEvent;
import io.github.moulberry.notenoughupdates.events.ResetEntityHurtEvent;
import io.github.moulberry.notenoughupdates.miscfeatures.DamageCommas;
import io.github.moulberry.notenoughupdates.overlays.BonemerangOverlay;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.IChatComponent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RendererLivingEntity.class)
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase> {
	@Redirect(method = "renderName", at = @At(value = "INVOKE", target =
		"Lnet/minecraft/entity/EntityLivingBase;getDisplayName()Lnet/minecraft/util/IChatComponent;"))
	public IChatComponent renderName_getDisplayName(EntityLivingBase entity) {
		if (entity instanceof EntityArmorStand) {
			return DamageCommas.replaceName(entity);
		} else {
			return entity.getDisplayName();
		}
	}

	@Inject(method = "getColorMultiplier", at = @At("HEAD"), cancellable = true)
	public void getColorMultiplier(
		T entity, float lightBrightness,
		float partialTickTime, CallbackInfoReturnable<Integer> cir
	) {
		RenderMobColoredEvent event = new RenderMobColoredEvent(entity, 0);
		event.post();
		cir.setReturnValue(event.getColor());

		if (BonemerangOverlay.INSTANCE.bonemeragedEntities.contains(entity) &&
			NotEnoughUpdates.INSTANCE.config.itemOverlays.highlightTargeted) {
			cir.setReturnValue(0x80ff9500);
		}
	}

	@Redirect(method = "setBrightness", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;hurtTime:I", opcode = Opcodes.GETFIELD))
	private int changeHurtTime(EntityLivingBase entity) {
		ResetEntityHurtEvent event = new ResetEntityHurtEvent(entity, false);
		event.post();
		return event.getShouldReset() ? 0 : entity.hurtTime;
	}
}
