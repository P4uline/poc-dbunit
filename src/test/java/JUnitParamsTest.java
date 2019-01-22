import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {

    @Test
    @Parameters({"3", "6", "9", "33"})
    public void should_test_with_dbunit(int i) {
        List mock = Mockito.mock(List.class);

        Assertions.assertThat(i).isBetween(0, 34);
    }
}
