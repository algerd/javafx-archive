package ru.javafx.jfxtest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javafx.jfxintegrity.BaseFxmlView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ButtonsController.class, ButtonsView.class})
public class ButtonsViewTest {
	
	@Autowired
	private ButtonsView buttonsView;
	
	@Autowired
	private ButtonsController buttonsController;
	
	@Test
	public void writeMe() throws Exception {
		Assert.assertThat(buttonsView, CoreMatchers.isA(BaseFxmlView.class));
		
		buttonsController.topButtonClicked();
	}
}
