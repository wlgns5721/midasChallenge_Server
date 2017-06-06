package midas;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * 화면 우측 탭들을 담는 Pane
 * @author dnjsd
 *
 */
public class TappedPane extends JTabbedPane {
	private ArrayList<PanelInformation> infos = new ArrayList<PanelInformation>();
	
	public TappedPane() {
		initUI();
	}
	
	public void initUI() {
		setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
			@Override
			protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
				return 40;
			}
		});
	}
	
	public void addInfo(PanelInformation getInfo){
		this.infos.add(getInfo);
		EditPanel jp = new EditPanel(getInfo);
		addTab(getInfo.getDocumentName(), jp);
		setTabComponentAt(infos.size()-1, new CustomTab(this));
	}

	public ArrayList<PanelInformation> getInfos() {
		return infos;
	}

	public void setInfos(ArrayList<PanelInformation> infos) {
		this.infos = infos;
	}
	
	public PanelInformation getNowSelectedInfo(){
		System.out.println("in getNowSelected : " + infos.get(this.getSelectedIndex()).getDocumentName());
		return infos.get(this.getSelectedIndex());
	}
}
