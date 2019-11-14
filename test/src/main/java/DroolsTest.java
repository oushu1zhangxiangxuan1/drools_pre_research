import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsTest{
    public static final void main(String[] args){
        try{
            //load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession  = kContainer.newKieSession("helloWorldSession");

            // go !
            // Message message = new Message();
            kSession.fireAllRules();
            kSession.dispose();
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}