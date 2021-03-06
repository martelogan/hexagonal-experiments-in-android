/*
 * hexagonal-experiments-in-android
 * Copyright (C) 2017, Logan Martel, Frederick Parsons
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.catandroid.app.common.components.utilities.hex_grid_utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by logan on 2017-01-31.
 */

public class AxialHexLocation
{
    public AxialHexLocation(int q, int r)
    {
        this.q = q;
        this.r = r;
    }

    public final int q;
    public final int r;

    static public AxialHexLocation addAxial(AxialHexLocation a, AxialHexLocation b)
    {
        int q = a.q + b.q;
        int r = a.r + b.r;
        return new AxialHexLocation(q, r);
    }

    // for pointy-oriented hex, directions are clockwise from 0->5 with 0 at top-right edge
    static public ArrayList<AxialHexLocation> axialDirections = new ArrayList<AxialHexLocation>(){{
        add(new AxialHexLocation(1, -1)); add(new AxialHexLocation(1, 0));
        add(new AxialHexLocation(0, 1)); add(new AxialHexLocation(-1, 1));
        add(new AxialHexLocation(-1, 0)); add(new AxialHexLocation(0, -1));}};

    static public AxialHexLocation axialDirection(int direction)
    {
        return AxialHexLocation.axialDirections.get(direction);
    }

    static public int complementAxialDirection(int direction)
    {
        return (direction + 3) % 6;
    }

    static public AxialHexLocation axialNeighbor(AxialHexLocation hexLocation, int direction)
    {
        return AxialHexLocation.addAxial(hexLocation, AxialHexLocation.axialDirection(direction));
    }

    static public boolean isCloseTo(AxialHexLocation a, Collection<AxialHexLocation> locations)
    {
        Boolean inQrange, inRrange;
        for (AxialHexLocation location : locations) {
            inQrange = location.q == (a.q - 1) || location.q == a.q || location.q == (a.q + 1);
            inRrange = location.r == (a.r - 1) || location.r == a.r || location.r == (a.r + 1);
            if (inQrange && inRrange) {
                return true;
            }
        }
        return false;
    }

}