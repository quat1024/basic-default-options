package agency.highlysuspect.basicdefaultoptions.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.nio.file.Files;

@Mixin(Options.class)
public class OptionsMixin {
	@Shadow @Final private File optionsFile;
	@Unique private File defaultOptionsFile;
	
	@Inject(method = "<init>", at = @At("TAIL"))
	private void onInit(Minecraft minecraft, File file, CallbackInfo ci) {
		defaultOptionsFile = new File(file, "defaultoptions.txt");
	}
	
	@Inject(method = "load", at = @At("HEAD"))
	private void onLoad(CallbackInfo ci) {
		try {
			if(defaultOptionsFile.exists() && !optionsFile.exists()) {
				System.out.println("Copying default options");
				Files.copy(defaultOptionsFile.toPath(), optionsFile.toPath());
			}
		} catch (Exception e) {
			System.err.println("[simple-default-options] Trouble");
			e.printStackTrace();
		}
	}
}
