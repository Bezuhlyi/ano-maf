package net.anotheria.maf;

import net.anotheria.maf.action.Action;
import net.anotheria.maf.action.ActionFactory;
import net.anotheria.maf.action.DefaultActionFactory;
import net.anotheria.maf.action.ActionFactoryException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionFactoryTest {
	@Test public void testSingleton() throws ActionFactoryException{
		ActionFactory f = new DefaultActionFactory();
		Action a1 = f.getInstanceOf("net.anotheria.maf.TestAction");
		Action a2 = f.getInstanceOf("net.anotheria.maf.TestAction");
		assertSame(a1,a2);
	}
	
	@Test(expected=ActionFactoryException.class) public void nonExisting() throws ActionFactoryException {
		new DefaultActionFactory().getInstanceOf("nonexisting");
		fail("exception expected");
	}
}
