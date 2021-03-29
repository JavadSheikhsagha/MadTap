package ir.nikgostarr.madtap.api;

import com.google.gson.annotations.SerializedName;

public class VerModel1 {

    @SerializedName("version")
    private String version;

    // Getter Methods
    public String getVersion() {
        return version;
    }

    // Setter Methods
    public void setVersion(String version) {
        this.version = version;
    }
}
