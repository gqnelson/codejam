import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class MagicTrickTest {

    @Test
    public void testFirstPass() throws Exception {
        Set one = new HashSet<Integer>(Arrays.asList(1, 2));
        Set two = new HashSet<Integer>(Arrays.asList(2, 1));
        Assert.assertEquals(one, two);
    }
}