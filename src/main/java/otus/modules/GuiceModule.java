package otus.modules;

import com.google.inject.AbstractModule;
import otus.utils.ValueAccumulator;

public class GuiceModule extends AbstractModule {
  
  @Override
  protected void configure() {
    bind(ValueAccumulator.class).toInstance(new ValueAccumulator());
  }
  
}
