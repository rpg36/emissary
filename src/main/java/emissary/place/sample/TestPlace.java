package emissary.place.sample;

import emissary.core.DataObjectFactory;
import emissary.core.Form;
import emissary.core.IBaseDataObject;
import emissary.place.MultiFileServerPlace;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestPlace extends MultiFileServerPlace {

    public TestPlace(String cfgInfo, String dir, String placeLoc) throws IOException {
        super(cfgInfo, dir, placeLoc);
        configurePlace();
    }

    @Override
    protected void configurePlace() throws IOException {

    }

    @Override
    public List<IBaseDataObject> processHeavyDuty(IBaseDataObject d) {
        d.putParameter("PARENT_VALUE", "from parent");
        d.setCurrentForm(Form.TEXT);
        List<IBaseDataObject> attachments = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            IBaseDataObject child = DataObjectFactory.getInstance(d);
            child.setCurrentForm(Form.TEXT);
            child.setFilename("Foo" + i);
            child.setFileType("txt");
            child.setData(child.getFilename().getBytes(StandardCharsets.UTF_8));
            child.putParameter("Child", i);
            attachments.add(child);
        }

        return attachments;
    }

}
