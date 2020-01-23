package ueb03;
import com.sun.management.internal.DiagnosticCommandImpl;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class SetImplComparator<T> implements Set<T> {

    class Element{
        T value;
        Element left, right;
        Element(T v, Element l, Element r){
            value = v;
            left = l;
            right = r;
        }

        int size(){
            return 1
                        +(left == null ? 0 : left.size())
                        +(right == null ? 0 : right.size());
        }

        public String toString(){
            return value
                            + (left == null ? "" : ", " + left)
                            + (right == null ? "" : ", " + right);
        }
    }

    Element root;

    Comparator<T> comp;

    public  SetImplComparator(Comparator<T> comp){
        this.comp = comp;
    }

    @Override
    public boolean add(T s){
        return addElement(new Element(s, null, null));

    }

    private boolean addElement (Element e) {
        if (e == null)
            return false;

        if (root == null) {
            root = e;
            return true;
        }

        Element it = root;
        while (it != null) {
            int c = comp.compare(e.value, it.value);
            if (c == 0)
                return false;
            else if (c < 0) {
                if (it.left == null) {
                    it.left = e;
                    return true;
                } else
                    it = it.left;
            } else {
                if (it.right == null) {
                    it.right = e;
                    return true;
                } else
                    it = it.right;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T s){
        if (root == null)
            return false;

        Element it = root;
        while (it != null){
            int c = comp.compare(s, it.value);
            if (c == 0)
                return true;
            else if (c < 0){
                it = it.left;
            } else {
                it = it.right;
            }
        }

        // nicht gefunden!
        return false;
    }

    @Override
    public T remove(T s){
        if (root == null)
            throw new NoSuchElementException();

        //Spezialfall: Root Element loeschen
        if (root.value.equals(s))
            return removeRoot();

        Element it = root;
        while (it != null){
            if (comp.compare(s, it.value) < 0){
                if (it.left != null && it.left.value.equals(s))
                    return removeElement(it, it.left);
                it = it.left;
            } else {
                if (it.right != null && it.right.value.equals(s))
                    return removeElement(it, it.right);
                it = it.right;
            }
        }
        throw new NoSuchElementException();
    }

    private T removeRoot(){
        assert (root != null);

        Element e = root;
        if (e.left == null && root.right == null){
            //eine Kinder -> Baum leer
            root = null;
        } else if (e.left == null){
            //nur ein rechtes Kind -> das ist der neue Bau
            root = e.right;
        } else if (e.right == null){
            //dito, für Links
            root = e.left;
        } else {
            //eines wird root, anderes einfügen
            root = e.left;
            addElement(e.right);
        }

        //Wert zurueck geben
        return e.value;
    }

    /**
     *
     * @param p Elternelement
     * @param e zu loeschendes Element
     */
    private T removeElement(Element p, Element e) {
        if (e == p.left) {
            p.left = null;  // links abgestiegen
        } else {
            p.right = null;  // sonst: rechts
        }

        // Kinder einfuegen
        addElement(e.left);
        addElement(e.right);

        return e.value;
    }

    @Override
    public int size(){
        if (root == null){
            return 0;
        } else {
            return root.size();
        }
    }

    @Override
    public String toString(){
        if (root == null){
            return "[]";
        } else {
            return "[" + root.toString() + "]";
        }
    }
}
