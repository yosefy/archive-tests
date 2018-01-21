package org.bb.qa.common.configuration;

import org.apache.commons.lang3.StringUtils;
import org.bb.qa.common.TestContext;
import org.bb.qa.common.annotations.InBrowser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration handler. This Class should handle run configuration and global properties.
 * Configuration handling:
 * <ol>
 * <li>Look for the property key in testConfig map, if key is present, return the value</li>
 * <li>Look for the property key in system properties - return value of this property if key present
 * </li>
 * <li>If no System Property is found - value is provided from configuration files
 * (config.properties and config_local.properties). Values provided in config.properties, are overriding values from
 * config_local.properties</li>
 * </ol>
 */
public class Configuration {

    private static final String DEFAULT_CONFIG_FILE_NAME = "config.properties";
    private static final String LOCAL_CONFIG_FILE_NAME = "config_local.properties";

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static Properties defaultConfig;
    private static Properties testConfig = new Properties();

    private Configuration() {
    }

    private static Properties readConfiguration() {
        if (defaultConfig != null) {
            return defaultConfig;
        }

        try {
            defaultConfig = new Properties();
            defaultConfig.load(new FileInputStream(new File(DEFAULT_CONFIG_FILE_NAME)));
        } catch (IOException e) {
            throw new IllegalStateException(
                    String.format("CANNOT FIND DEFAULT CONFIG FILE : %s", DEFAULT_CONFIG_FILE_NAME), e);
        }

        File localConfigFile = new File(LOCAL_CONFIG_FILE_NAME);
        if (localConfigFile.exists()) {
            try {
                Properties localConfig = new Properties();
                localConfig.load(new FileInputStream(localConfigFile));
                defaultConfig.putAll(localConfig);
            } catch (IOException e) {
                logger.info("Error loading local config file", e);
            }
        } else {
            logger.info("local config file does not exist");
        }

        return defaultConfig;
    }

    private static String getProp(String key) {
        String value = testConfig.getProperty(key);
        if (value == null) {
            value = System.getProperty(key);
            if (value == null) {
                value = readConfiguration().getProperty(key);
            }
        }

        return value;
    }

    public static String getBrowser() {
        return getProp("browser");
    }

    public static String getDpr() {
        return getProp("dpr");
    }

    public static String getEnv() {
        return getProp("env");
    }

    public static String getSiteUrl() {
        return getProp("siteUrl");
    }

    public static String getPlatform() {
        return getProp("platform");
    }

    public static Emulator getEmulator() {
        Emulator emulatorToUse = Emulator.DEFAULT;
        if (TestContext.getCurrentTestMethod().getDeclaringClass()
                .isAnnotationPresent(InBrowser.class)) {
            emulatorToUse = TestContext.getCurrentTestMethod().getDeclaringClass()
                    .getDeclaredAnnotation(InBrowser.class).emulator();
        }
        if (TestContext.getCurrentTestMethod().isAnnotationPresent(InBrowser.class)) {
            emulatorToUse =
                    TestContext.getCurrentTestMethod().getDeclaredAnnotation(InBrowser.class).emulator();
        }
        return emulatorToUse;
    }

    public static EnvType getEnvType() {
        return getEnvType(getEnv());
    }

    public static EnvType getEnvType(String env) {
        if (env.contains("production")) {
            return EnvType.PRODUCTION;
        } else if (env.contains("staging")) {
            return EnvType.STAGING;
        } else if (env.contains("dev")) {
            return EnvType.DEV;
        }
        return EnvType.PRODUCTION;
    }

    public static void setTestValue(String key, String value) {
        testConfig.put(key, value);
    }

    public static void clearCustomTestProperties() {
        testConfig.clear();
    }

    /**
     * @return null if window is supposed to be maximised, Dimension if any other size is demanded
     */
    public static Dimension getBrowserSize() {
        String size = getProp("browserSize");

        if (StringUtils.isNotBlank(size) || "maximised".equals(size) || size.split("x").length == 2) {
            if ("maximised".equals(size)) {
                return null;
            } else {
                return new Dimension(Integer.valueOf(size.split("x")[0]),
                        Integer.valueOf(size.split("x")[1]));
            }
        } else {
            throw new WebDriverException("browser size: " + size + " is not a proper value");
        }
    }

}
