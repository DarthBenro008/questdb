/*******************************************************************************
 *    ___                  _   ____  ____
 *   / _ \ _   _  ___  ___| |_|  _ \| __ )
 *  | | | | | | |/ _ \/ __| __| | | |  _ \
 *  | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *   \__\_\\__,_|\___||___/\__|____/|____/
 *
 * Copyright (C) 2014-2018 Appsicle
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package com.questdb.ql.map;

import com.questdb.std.ObjList;
import com.questdb.std.ThreadLocal;
import com.questdb.store.RecordColumnMetadata;

public class VirtualColumnTypeResolver implements ColumnTypeResolver {
    private ObjList<? extends RecordColumnMetadata> virtualColumns = new ObjList<>();

    @Override
    public int count() {
        return virtualColumns.size();
    }

    @Override
    public int getColumnType(int index) {
        assert index < virtualColumns.size();
        return virtualColumns.getQuick(index).getType();
    }

    public VirtualColumnTypeResolver of(ObjList<? extends RecordColumnMetadata> virtualColumnList) {
        this.virtualColumns = virtualColumnList;
        return this;
    }

    public static class ResolverThreadLocal extends ThreadLocal<VirtualColumnTypeResolver> {
        public ResolverThreadLocal() {
            super(VirtualColumnTypeResolver::new);
        }
    }
}