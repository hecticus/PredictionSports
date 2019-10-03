package services.kraken_servicio;

import modeles.Alta;
import services.dto.AltaKrakenPeticionDto;

public class AltaToAltaKrakenMapper {
    public AltaKrakenPeticionDto mapTo(Alta alta) {
        return new AltaKrakenPeticionDto(alta.getMsisdn(), "9090", "msg");
    }
}
