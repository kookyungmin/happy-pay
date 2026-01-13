package net.happykoo.membership.adapter.out.vault;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.happykoo.membership.application.port.out.FindSecretValuePort;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport.KeyValueBackend;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class VaultAdapter implements FindSecretValuePort {

  private final VaultTemplate vaultTemplate;

  @Override
  public String findEncryptionKey() {
    VaultKeyValueOperations ops = vaultTemplate.opsForKeyValue("kv-v1",
        KeyValueBackend.KV_2);
    return (String) Optional.ofNullable(ops.get("encrypt/data/dbkey"))
        .map(VaultResponseSupport::getData)
        .map(data -> data.get("key"))
        .orElse(null);
  }
}
