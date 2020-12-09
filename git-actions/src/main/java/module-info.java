import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.argument.ArgumentProvider;
import com.aptkode.nemo.git.argument.GitActionsArgumentProvider;
import com.aptkode.nemo.git.GitCheckOutAction;
import com.aptkode.nemo.git.GitCloneAction;
import com.aptkode.nemo.git.GitPushAction;

module git.actions {
    exports com.aptkode.nemo.git;
    exports com.aptkode.nemo.git.argument to com.fasterxml.jackson.databind;
    exports com.aptkode.nemo.git.key to com.fasterxml.jackson.databind;
    requires nemo.api;
    requires org.eclipse.jgit;
    provides Action with GitCloneAction, GitCheckOutAction, GitPushAction;
    provides ArgumentProvider with GitActionsArgumentProvider;
}