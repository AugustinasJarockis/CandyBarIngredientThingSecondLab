package candyBar.usecases;

import candyBar.interceptors.TimeLogger;
import candyBar.persistence.CandyBarsDAO;
import candyBar.entities.CandyBar;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CandyBars {
    @Inject
    private CandyBarsDAO candyBarsDAO;
    @Getter
    private List<CandyBar> allCandyBars;

    @Getter @Setter
    private CandyBar candyBarToCreate = new CandyBar();

    @PostConstruct
    public void init(){
        loadAllCandyBars();
    }
    @Transactional
    public void createCandyBar(){
        this.candyBarsDAO.persist(candyBarToCreate);
    }
    @TimeLogger
    private void loadAllCandyBars(){
        this.allCandyBars = candyBarsDAO.loadAll();
    }
}
