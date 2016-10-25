package InLifeMatchMaker;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.core.Application;


public class WebService_Config extends Application{

	  private static final Set<Class<?>> CLASSES;

	  static {
	    HashSet<Class<?>> tmp = new HashSet<Class<?>>();
	    tmp.add(Resource.class);

	    CLASSES = Collections.unmodifiableSet(tmp);
	  }
	  
	  @Override
	  public Set<Class<?>> getClasses() {

	    return CLASSES;
	  }
}
