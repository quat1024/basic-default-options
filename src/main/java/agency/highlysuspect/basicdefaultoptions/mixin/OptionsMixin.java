package agency.highlysuspect.basicdefaultoptions.mixin;

import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(Options.class)
public class OptionsMixin {
	@Shadow @Final private File optionsFile;
	
	@Inject(method = "load", at = @At("HEAD"))
	private void onLoad(CallbackInfo ci) {
		try {
			Path optionsPath = optionsFile.toPath();
			Path defaultOptionsPath = optionsPath.resolveSibling("defaultoptions.txt");
			
			if(!Files.exists(optionsPath) && Files.exists(defaultOptionsPath)) {
				System.out.println("[basic-default-options] Copying default options");
				Files.copy(defaultOptionsPath, optionsPath);
			}
		} catch (Exception e) {
			System.err.println("[basic-default-options] Trouble");
			e.printStackTrace();
		}
	}
}
