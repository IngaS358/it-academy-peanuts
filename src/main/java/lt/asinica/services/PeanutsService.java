package lt.asinica.services;

import lt.asinica.beans.Peanut;
import lt.asinica.beans.PeanutList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by p998snc on 2016.02.10.
 */

@RestController
public class PeanutsService {

    private PeanutList peanutList;

    @PostConstruct
    public void init() {
        peanutList = new PeanutList();
        peanutList.addPeanut(new Peanut("Karolis"));
        peanutList.addPeanut(new Peanut("Antanas"));
        peanutList.addPeanut(new Peanut("Beata"));
        peanutList.addPeanut(new Peanut("Inga"));
        peanutList.addPeanut(new Peanut("jonas"));
    }

    @RequestMapping("/peanuts")
    public PeanutList list() {
       return peanutList;
    }

    @RequestMapping(value="/peanuts/sorted")
    public PeanutList getSortedList(){
        Collections.sort(peanutList.getPeanuts());
        return peanutList;
    }

    @RequestMapping(value="/peanuts/ID3")
    public List divisionFrom3()
    {
        List<Peanut> temp = new ArrayList<Peanut>();

        for(Peanut peanut: peanutList.getPeanuts())
        {
            if(peanut.getId() % 3 == 0)
                temp.add(peanut);
        }
        return temp;
    }



    @RequestMapping(value="/peanuts", method = RequestMethod.PUT)
    public void create(String name) {
        peanutList.addPeanut(new Peanut(name));
    }

    @RequestMapping(value="/peanuts/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        Peanut toRemove = null;
        for(Peanut p : peanutList.getPeanuts()) {
            if(p.getId() == id) {
                toRemove = p;
            }
        }

        if(toRemove != null)
            peanutList.getPeanuts().remove(toRemove);

    }
    @RequestMapping(value="/peanut/{id}")
    public Peanut get(@PathVariable Long id) {
        return peanutList.getPeanuts().get(id.intValue());
    }

}
