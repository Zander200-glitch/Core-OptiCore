import com.jme3.math.Vector3f;
import org.lwjgl.Sys;

public class mod_opticore_main {

    // opticore info
    private static String ModAuthor = "Zander_200";
    private static float ModVersion = 0.61f;
    private static String ModName = "OptiCore";

    // opticore visual settings
    private static int oc_renderDistance = 5; // render distance
    public static int getOc_renderDistance(){ return oc_renderDistance; }

    private static int oc_maxSpeed = (oc_renderDistance * 7);
    public static int getOc_maxSpeed(){return oc_maxSpeed; }

    // opticore gameplay settings
    private static boolean oc_isFlyEnabled = false;
    public static boolean getOc_isFlyEnabled(){return oc_isFlyEnabled; }

    // opticore debug settings
    private static boolean oc_generalDebugOutput = true;
    public static boolean isOc_generalDebugOutput(){return oc_generalDebugOutput; }

    //opticore return completion
    private static String oc_getClientOutputSignature(){ return "[" + ModName + "] "; }
    public static String oc_getCoreOutputSignature(){return "[CrafterEngine] "; }

    //opticore main
    public static void oc_main(){
        System.out.println(oc_getClientOutputSignature() + ModName + ModVersion + " is starting.");
    }

    public static void oc_printCoreError(String errmsg){
        System.out.println(oc_getClientOutputSignature() + "ERROR: " + errmsg);
    }
}
