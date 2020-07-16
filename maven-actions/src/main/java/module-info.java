import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.maven.MavenAddPropertyAction;
import com.aptkode.nemo.maven.MavenExecAction;
import com.aptkode.nemo.maven.MavenUpdateDependencyAction;

module maven.actions {
    requires org.apache.commons.io;
    requires java.xml;
    requires org.codehaus.stax2;
    requires nemo.api;
    provides Action with MavenExecAction, MavenAddPropertyAction, MavenUpdateDependencyAction;
}