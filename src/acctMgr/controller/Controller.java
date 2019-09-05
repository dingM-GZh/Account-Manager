package acctMgr.controller;
import acctMgr.view.View;
import acctMgr.model.Model;

public interface Controller {
	void setModel(Model model);
	void setView(View view);
	Model getModel();
	View getView();
	
}
