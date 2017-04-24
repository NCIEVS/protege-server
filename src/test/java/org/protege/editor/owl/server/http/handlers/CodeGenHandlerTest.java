package org.protege.editor.owl.server.http.handlers;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.protege.editor.owl.server.http.handlers.CodeGenHandler;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class CodeGenHandlerTest {

    @Test
    public void generateNTokens() {
        List<String> codes = CodeGenHandler.generateCodes(10, 5, "p", "s", "_");
        List<String> expected = Arrays.asList("p_10_s",
                                              "p_11_s",
                                              "p_12_s",
                                              "p_13_s",
                                              "p_14_s");
        assertThat(codes, is(expected));
    }

    @Test
    public void generateTokensNulls() {
        List<String> codes = CodeGenHandler.generateCodes(9, 3, "C", null, null);
        List<String> expected = Arrays.asList("C9", "C10", "C11");
        assertThat(codes, is(expected));
    }
}
