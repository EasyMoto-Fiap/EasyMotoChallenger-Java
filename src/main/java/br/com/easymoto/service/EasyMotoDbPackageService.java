package br.com.easymoto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Service;

import oracle.jdbc.OracleTypes;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EasyMotoDbPackageService {

    private final JdbcTemplate jdbcTemplate;

    public void executarMotosJson(Integer clienteId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_EASYMOTO")
                .withProcedureName("MOTOS_JSON");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_CLIENTE_ID", clienteId);

        call.execute(params);
    }

    public List<Map<String, Object>> resumoLocacoes() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_EASYMOTO")
                .withProcedureName("RESUMO_LOCACAO")
                .declareParameters(
                        new SqlOutParameter("P_OUT", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> out = call.execute();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rows = (List<Map<String, Object>>) out.get("P_OUT");
        return rows;
    }

    public String getRowJson(String tableName, String pkColumn, Long pkValue) {
        String sql = "SELECT PKG_EASYMOTO.get_row_json(?, ?, ?) FROM dual";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{tableName, pkColumn, pkValue.toString()},
                String.class
        );
    }
}
