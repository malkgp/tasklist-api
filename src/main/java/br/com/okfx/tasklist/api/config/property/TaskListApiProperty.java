package br.com.okfx.tasklist.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tasklist")
public class TaskListApiProperty {

    private String originPermitida = "http://localhost:3000";
    private final Security security = new Security();

    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    public Security getSecurity() {
        return security;
    }

    public static class Security {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

    }

}
