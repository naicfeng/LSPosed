/*
 * This file is part of LSPosed.
 *
 * LSPosed is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LSPosed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LSPosed.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2020 EdXposed Contributors
 * Copyright (C) 2021 LSPosed Contributors
 */

package org.lsposed.lspd.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.apksig.ApkVerifier;

import java.io.File;
import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class InstallerVerifier {

    private static final byte[] CERTIFICATE = {48, -126, 3, 99, 48, -126, 2, 75, -96, 3, 2, 1, 2, 2, 4, 35, 127, 7, -54, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 48, 98, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 15, 48, 13, 6, 3, 85, 4, 8, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 7, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 10, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 11, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 3, 19, 6, 87, 117, 89, 97, 110, 103, 48, 30, 23, 13, 50, 49, 48, 49, 50, 55, 48, 54, 48, 49, 50, 50, 90, 23, 13, 52, 54, 48, 49, 50, 49, 48, 54, 48, 49, 50, 50, 90, 48, 98, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 15, 48, 13, 6, 3, 85, 4, 8, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 7, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 10, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 11, 19, 6, 87, 117, 89, 97, 110, 103, 49, 15, 48, 13, 6, 3, 85, 4, 3, 19, 6, 87, 117, 89, 97, 110, 103, 48, -126, 1, 34, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -126, 1, 15, 0, 48, -126, 1, 10, 2, -126, 1, 1, 0, -127, 56, 64, 39, -45, -59, -11, 72, -73, 63, -87, 9, -64, 45, -37, 24, 104, 109, -36, -43, -109, -112, 66, -1, -34, 87, -98, 108, -79, -96, -3, 24, -44, -103, -23, -40, 33, -40, -65, 9, 123, -28, 125, 53, -109, -36, -83, -89, 64, -3, -13, 81, 9, -115, 60, 104, -74, 89, -114, 29, 121, -11, 107, -98, 57, 115, 64, -60, -105, -55, 81, 43, -21, -90, 29, -6, -55, -122, 40, -102, 102, -19, 71, -8, -19, 74, -90, -95, -31, 64, -32, 60, -68, 64, 64, -119, -14, 38, -3, -74, -107, -30, 46, -17, -57, -3, -14, 72, -52, 99, -100, -40, 32, 50, 67, -106, 103, -15, 61, 79, 119, 91, 54, 48, 26, 24, -126, -75, 16, 61, -87, 6, 28, 5, 17, -40, -60, 110, -97, 64, -25, -99, -39, 3, -64, 9, -117, 86, -53, -27, 4, 111, -51, 26, -92, 109, -101, 121, -31, -46, -29, -6, 2, 59, 60, 49, -25, 57, 30, 21, -20, -49, 50, -120, 63, 22, -89, -41, 84, 66, 91, -116, 126, -4, 16, 121, -17, -49, 6, -106, 120, 89, -126, 17, 50, 94, -51, 83, 110, 69, 92, 112, 119, -47, -57, 120, 105, -44, 56, 122, 67, -86, 2, 39, 78, 104, -78, -54, 16, 89, -75, 82, 27, 92, 73, 112, 31, 103, 94, 103, -3, 122, 125, -124, -105, 112, 84, 120, 78, -111, 88, -79, -76, 104, -54, 7, 63, 81, 77, -98, -121, 18, -124, 33, -19, 39, 2, 3, 1, 0, 1, -93, 33, 48, 31, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, 121, 37, 19, -16, -43, 74, -93, 18, -21, -82, -119, -42, -90, -109, 69, 98, 99, 32, -68, -68, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 3, -126, 1, 1, 0, 23, 93, -74, 99, -49, -25, -67, -11, 43, 82, 10, 3, 70, 104, -24, -87, 40, 87, 64, 117, 19, 45, 44, 88, 119, 31, -55, -107, 39, -81, 12, -73, -44, 9, -126, 25, 108, -69, 57, -110, 83, -37, -15, -124, -11, -98, 89, -109, -54, -127, 117, -85, 54, 66, -128, -97, 27, 111, -29, -47, -63, -82, 127, 0, -14, -113, -12, 93, 112, -114, -37, 27, -74, -83, 36, 27, -54, 91, -36, 114, -81, -19, -84, -98, -35, 80, -77, -124, -14, 79, 43, -121, 14, -46, 77, -125, 109, -101, -30, -27, -48, 109, 19, -29, -127, 121, -93, -70, 42, 90, 78, 12, 86, -118, -110, -71, -109, 78, -1, -39, 8, 94, -72, -127, -57, 22, -49, -26, -122, -122, -7, -30, 100, 63, 38, -3, 27, -18, 103, -22, 82, -99, 12, 127, 113, -16, -87, -32, 34, 34, 0, 81, 4, -43, -32, -100, -71, 20, -85, -128, 82, 117, -128, 86, 92, -9, 124, 47, 71, -41, -125, -126, -7, 25, -36, 49, -64, -12, 48, 122, -119, 1, 29, -110, 47, 38, 54, 83, 35, -69, -96, 22, 43, -79, -41, 17, -11, 46, 2, -81, -17, -116, 43, -46, 58, 0, 13, 40, 25, -3, 87, 20, 26, 84, -19, 112, 25, 29, -120, -103, 49, -84, 122, -102, 107, -122, 21, 11, 58, -34, 14, 110, -23, -50, 120, -101, -7, 25, 7, -77, 23, 92, -78, 67, -82, 102, 14, -72, 29, 22, -96, 88, -105, -23, 41, 36};

    public static boolean verifyInstallerSignature(ApplicationInfo appInfo) {
        if ((appInfo.flags & ApplicationInfo.FLAG_TEST_ONLY) != 0) {
            return true;
        }
        ApkVerifier verifier = new ApkVerifier.Builder(new File(appInfo.sourceDir))
                .setMinCheckedPlatformVersion(26)
                .build();
        try {
            ApkVerifier.Result result = verifier.verify();
            if (!result.isVerified()) {
                return false;
            }
            return Arrays.equals(result.getSignerCertificates().get(0).getEncoded(), CERTIFICATE);
        } catch (Throwable t) {
            Utils.logE("verifyInstallerSignature: ", t);
            return false;
        }
    }

    public static void hookXposedInstaller(final ClassLoader classLoader) {
        try {
            Class<?> ConstantsClass = XposedHelpers.findClass("org.lsposed.manager.Constants", classLoader);
            XposedHelpers.findAndHookMethod(android.app.Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        XposedHelpers.callStaticMethod(ConstantsClass, "showErrorToast", 0);
                    } catch (Throwable t) {
                        Utils.logW("showErrorToast: ", t);
                        Toast.makeText((Context) param.thisObject, "This application has been destroyed, please make sure you download it from the official source.", Toast.LENGTH_LONG).show();
                    }
                    new Handler().postDelayed(() -> System.exit(0), 50);
                }
            });
        } catch (Throwable t) {
            Utils.logW("hookXposedInstaller: ", t);
        }
    }
}
