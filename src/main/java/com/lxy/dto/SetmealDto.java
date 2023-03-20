package com.lxy.dto;


import com.lxy.entity.Setmeal;
import com.lxy.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
