package controller;

import main.com.test.task.aleshin.dmitriy.controller.DefaultController;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultControllerTest extends TestCase {

    private DefaultController defaultController;
    @BeforeClass

    public void setUp() throws Exception {
        defaultController = new DefaultController();
    }

    @Test
    public void testNameHtmlPage() {
        assertEquals(defaultController.index(), "index");
    }
}
