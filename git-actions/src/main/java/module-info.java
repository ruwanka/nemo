import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.git.GitCheckOutAction;
import com.aptkode.nemo.git.GitCloneAction;
import com.aptkode.nemo.git.GitPushAction;

module git.actions {
    requires nemo.api;
    requires org.eclipse.jgit;
    provides Action with GitCloneAction, GitCheckOutAction, GitPushAction;
}