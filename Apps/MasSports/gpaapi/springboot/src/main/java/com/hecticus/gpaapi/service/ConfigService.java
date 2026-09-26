package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.domain.Config;
import com.hecticus.gpaapi.repository.ConfigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConfigService {

    private final ConfigRepository repository;

    public ConfigService(ConfigRepository repository) {
        this.repository = repository;
    }

    public String getString(String key) {
        Optional<Config> config = repository.findByConfigKey(key);
        return config.map(Config::getValue).orElse(null);
    }

    @Transactional
    public void setString(String key, String value) {
        setString(key, value, null);
    }

    @Transactional
    public void setString(String key, String value, String description) {
        Config config = repository.findByConfigKey(key).orElse(null);
        if (config != null) {
            config.setValue(value);
            if (description != null) {
                config.setDescription(description);
            }
            repository.save(config);
        } else {
            config = new Config();
            config.setConfigKey(key);
            config.setValue(value);
            config.setDescription(description);
            repository.save(config);
        }
    }
}
