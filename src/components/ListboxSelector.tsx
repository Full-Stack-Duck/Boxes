import { Fragment, useEffect, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
// import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import { CaretDown } from "phosphor-react";
import { CheckFat } from '@phosphor-icons/react';
interface PeriodoProps {
  name: string,
  periodo: number
}

const periodoSelect = [
  { name: 'Últimos 30 dias', periodo: 30},
  { name: 'Últimos 90 dias' , periodo: 90},
  { name: 'Últimos 180 dias', periodo: 180},
  { name: 'Últimos 360 dias', periodo: 360},
]


export default function ListboxSelector() {
  const [selected, setSelected] = useState<PeriodoProps>(periodoSelect[0])

  return (
    <div className="relative w-52 p-2 whitespace-nowrap lg:p-0">
      <Listbox value={selected} onChange={setSelected}>
        <div className="relative mt-1">
          <Listbox.Button className="relative font-bold font-quicksand w-full py-2 pl-3 pr-7 text-left focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm">
            <span className="block truncate">{selected.name}</span>
            <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
            <CaretDown size={16} color="#030303" weight="fill" aria-hidden="true" className='-ml-4 -mb-2'/>
            </span>
          </Listbox.Button>
          <Transition
            as={Fragment}
            leave="transition ease-in duration-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <Listbox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
              {periodoSelect.map((person, personIdx) => (
                <Listbox.Option
                  key={personIdx}
                  className={({ active }) =>
                    `relative cursor-default select-none py-2 pl-10 pr-4 ${
                      active ? 'bg-purple-200 text-purple-dark' : 'text-purple-950'
                    }`
                  }
                  value={person.periodo}
                >
                  {({ selected }) => (
                    <>
                      <span
                        className={`block truncate ${
                          selected ? 'font-medium' : 'font-normal'
                        }`}
                      >
                        {person.name}
                      </span>
                      {selected ? (
                        <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-purple-950">
                          <CheckFat size={17} aria-hidden="true"/>  
                        </span>
                      ) : null}
                    </>
                  )}
                </Listbox.Option>
              ))}
            </Listbox.Options>
          </Transition>
        </div>
      </Listbox>
    </div>
  )
}
