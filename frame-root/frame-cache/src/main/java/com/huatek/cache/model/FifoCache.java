package com.huatek.cache.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class FifoCache<Key, Value> implements LruCache<Key,Value> {

    final int limit;

    Map<Key, Value> map = new LinkedHashMap<Key, Value> () {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        protected boolean removeEldestEntry ( Map.Entry<Key, Value> eldest ) {
            return this.size () > limit;
        }
    };


    public FifoCache ( int limit ) {
        this.limit = limit;
    }

    public void put ( Key key, Value value ) {
         map.put ( key, value );


    }


    public Value get ( Key key ) {

        return map.get ( key );
    }


    public Value getSilent ( Key key ) {

        return map.get ( key );
    }

    public Value remove ( Key key ) {
        return map.remove ( key );
    }

    public int size () {
        return map.size ();
    }

    public String toString() {
        return map.toString ();
    }
}
