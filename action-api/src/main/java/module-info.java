import com.aptkode.nemo.api.ArgumentProvider;
import com.aptkode.nemo.api.CoreArgumentProvider;

module nemo.api {
    exports com.aptkode.nemo.api;
    provides ArgumentProvider with CoreArgumentProvider;
}