package otus.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import otus.modules.GuiceModule;

public class ApiExtension implements BeforeEachCallback, AfterEachCallback {
  
  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    Injector injector = Guice.createInjector(new GuiceModule());
    Object testInstance = context.getRequiredTestInstance();
    injector.injectMembers(testInstance);
  }
  
  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    Object testInstance = context.getRequiredTestInstance();
    if (testInstance instanceof RequiresCleanup) {
      ((RequiresCleanup) testInstance).cleanup();
    }
  }
  
}