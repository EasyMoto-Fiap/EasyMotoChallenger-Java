package br.com.easymoto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProcedureEasyMotoService {

    private final JdbcTemplate jdbcTemplate;

    public void finalizarLocacoesVencidasHoje() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_EASYMOTO_OPERACOES")
                .withProcedureName("PRC_FINALIZAR_LOCACOES_VENCIDAS");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_DATA_REF", Date.valueOf(LocalDate.now()));

        call.execute(params);
    }
}
