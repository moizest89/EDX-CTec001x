package moizest89.com.tipcalc.fragments;

import moizest89.com.tipcalc.model.TipRecord;

/**
 * Created by @moizest89 in SV on 6/22/16.
 */
public interface TipHistoryListFragmentListener {

    void addToList(TipRecord tipRecord);
    void clearList();
}
