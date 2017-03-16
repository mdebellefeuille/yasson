package org.eclipse.yasson.defaultmapping.basic;

import org.eclipse.yasson.TestTypeToken;
import org.eclipse.yasson.defaultmapping.generics.model.ScalarValueWrapper;
import org.junit.Assert;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;

/**
 * @author Roman Grigoriadi
 */
public class NumberTest {

    private Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void testSerializeFloat() {
        final String json = jsonb.toJson(0.35f);
        Assert.assertEquals("0.35", json);

        Float result = jsonb.fromJson("0.35", Float.class);
        Assert.assertEquals((Float).35f, result);
    }

    @Test
    public void testBigDecimalMarshalling() {
        String jsonString = jsonb.toJson(new BigDecimal("0.10000000000000001"));
        Assert.assertEquals("0.10000000000000001", jsonString);

        BigDecimal result = jsonb.fromJson("0.10000000000000001", BigDecimal.class);
        Assert.assertEquals(new BigDecimal("0.10000000000000001"), result);
    }

    @Test
    public void testBigDecimalWrappedMarshalling() {
        String jsonString = jsonb.toJson(new ScalarValueWrapper<>(new BigDecimal("0.10000000000000001")));
        Assert.assertEquals("{\"value\":0.10000000000000001}", jsonString);

        ScalarValueWrapper<BigDecimal> result = jsonb.fromJson("{\"value\":0.10000000000000001}", new TestTypeToken<ScalarValueWrapper<BigDecimal>>(){}.getType());
        Assert.assertEquals(new BigDecimal("0.10000000000000001"), result.getValue());
    }
}