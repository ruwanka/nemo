import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.core.action.ForEachInputAction;

module nemo.core {
    uses Action;
    requires nemo.api;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires org.apache.logging.log4j;
    exports com.aptkode.nemo.core to com.fasterxml.jackson.databind;
    exports com.aptkode.nemo.core.program;
    provides Action with ForEachInputAction;
}