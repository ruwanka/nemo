import com.aptkode.nemo.api.argument.ArgumentProvider;
import com.aptkode.nemo.api.argument.CoreArgumentProvider;

module nemo.api {
    exports com.aptkode.nemo.api;
    exports com.aptkode.nemo.api.key;
    exports com.aptkode.nemo.api.argument;
    requires com.fasterxml.jackson.annotation;
    provides ArgumentProvider with CoreArgumentProvider;
}