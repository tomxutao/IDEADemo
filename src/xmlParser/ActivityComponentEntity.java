package xmlParser;

public class ActivityComponentEntity {

    public String componentType;
    public String componentName;
    public String processName;
    public boolean exported;

    @Override
    public String toString() {
        return "ComponentEntity{" +
                "componentType='" + componentType + '\'' +
                ", componentName='" + componentName + '\'' +
                ", processName='" + processName + '\'' +
                ", exported=" + exported +
                '}';
    }
}
