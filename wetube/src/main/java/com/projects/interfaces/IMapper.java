package com.projects.interfaces;

import java.io.Serializable;

public interface IMapper<DOMAIN, DTO, WRAPPER> {
   DOMAIN fromDTOtoDOMAIN(DTO dto);

   DTO fromDOMAINtoDTO(Serializable id, DOMAIN domain);

   WRAPPER fromDOMAINtoWRAPPER(DOMAIN domain);

   WRAPPER fromDTOtoWRAPPER(DTO dto);
}
