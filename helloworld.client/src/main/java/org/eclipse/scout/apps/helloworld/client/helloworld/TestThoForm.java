package org.eclipse.scout.apps.helloworld.client.helloworld;

import org.eclipse.scout.apps.helloworld.client.helloworld.TestThoForm.MainBox.CancelButton;
import org.eclipse.scout.apps.helloworld.client.helloworld.TestThoForm.MainBox.OkButton;
import org.eclipse.scout.apps.helloworld.shared.helloworld.CreateTestThoPermission;
import org.eclipse.scout.apps.helloworld.shared.helloworld.ITestThoService;
import org.eclipse.scout.apps.helloworld.shared.helloworld.TestThoFormData;
import org.eclipse.scout.apps.helloworld.shared.helloworld.UpdateTestThoPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = TestThoFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class TestThoForm extends AbstractForm {

	@Override
	protected String getConfiguredTitle() {
		// TODO [austin] verify translation
		return TEXTS.get("TestTho");
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(100000)
		public class OkButton extends AbstractOkButton {
		}

		@Order(101000)
		public class CancelButton extends AbstractCancelButton {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ITestThoService service = BEANS.get(ITestThoService.class);
			TestThoFormData formData = new TestThoFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateTestThoPermission());
		}

		@Override
		protected void execStore() {
			ITestThoService service = BEANS.get(ITestThoService.class);
			TestThoFormData formData = new TestThoFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ITestThoService service = BEANS.get(ITestThoService.class);
			TestThoFormData formData = new TestThoFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateTestThoPermission());
		}

		@Override
		protected void execStore() {
			ITestThoService service = BEANS.get(ITestThoService.class);
			TestThoFormData formData = new TestThoFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
