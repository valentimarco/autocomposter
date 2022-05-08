package com.eslym.autocomposter.blocks.entities;

import com.eslym.autocomposter.Registries;
import com.eslym.autocomposter.blocks.PowerComposterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import static com.eslym.autocomposter.blocks.PowerComposterBlock.BOOSTABLES;

public class PowerComposterBlockEntity extends AutoComposterBlockEntity {

    protected static final String TAG_ENERGY = "energy";

    protected static final String TAG_FLUID = "water";

    protected EnergyStorage energy = new EnergyStorage(5000, 50, 50);

    protected LazyOptional<IEnergyStorage> lazyEnergy = LazyOptional.of(() -> energy);

    protected FluidTank fluid = new FluidTank(5000, f -> BOOSTABLES.containsKey(f.getFluid())) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }
    };

    protected LazyOptional<IFluidHandler> lazyFluid = LazyOptional.of(() -> fluid);

    public PowerComposterBlockEntity(BlockPos pos, BlockState state) {
        super(Registries.BlockEntities.POWER_COMPOSTER.get(), pos, state);
    }

    @Override
    protected int getConsumeCoolDown() {
        if(energy.getEnergyStored() >= 5){
            energy.extractEnergy(5, false);
            return 4;
        }
        return super.getConsumeCoolDown();
    }

    @Override
    protected int getCompostCoolDown() {
        if(energy.getEnergyStored() >= 10){
            energy.extractEnergy(10, false);
            return 10;
        }
        return super.getCompostCoolDown();
    }

    @Override
    protected float getChance(Item item) {
        float chance = super.getChance(item);
        if(fluid.getFluidAmount() >= 5){
            fluid.drain(5, IFluidHandler.FluidAction.EXECUTE);
            float boost = BOOSTABLES.getFloat(fluid.getFluid());
            chance *= boost;
        }
        return chance;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == CapabilityEnergy.ENERGY){
            return lazyEnergy.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            return lazyFluid.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt(TAG_ENERGY, energy.getEnergyStored());
        tag.putInt(TAG_FLUID, fluid.getFluidAmount());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        energy.deserializeNBT(tag.get(TAG_ENERGY));
        fluid.setFluid(new FluidStack(Fluids.WATER, tag.getInt(TAG_FLUID)));
    }
}
